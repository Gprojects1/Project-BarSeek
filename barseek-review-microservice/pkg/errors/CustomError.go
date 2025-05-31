package errors

import (
	"fmt"

	"github.com/pkg/errors"
)

const (
	NoType = ErrorType(iota)
	BadRequest
	NotFound
	NilUserId
	NilRole
	NotSaved
	WrongType
	EmptyData
)

type ErrorType uint
type customError struct {
	errorType     ErrorType
	originalError error
	contextInfo   errorContext
}

type errorContext struct {
	Field   string
	Message string
}

func (error customError) Error() string {
	return error.originalError.Error()
}

func (t ErrorType) New(msg string) error {
	return customError{errorType: t, originalError: errors.New(msg)}
}

func (t ErrorType) Newf(msg string, args ...interface{}) error {
	err := fmt.Errorf(msg, args...)

	return customError{errorType: t, originalError: err}
}

func (t ErrorType) Wrap(err error, msg string) error {
	return t.Wrapf(err, msg)
}

func (t ErrorType) Wrapf(err error, msg string, args ...interface{}) error {
	newErr := errors.Wrapf(err, msg, args...)

	return customError{errorType: t, originalError: newErr}
}

func AddErrorContext(err error, field, message string) error {
	context := errorContext{Field: field, Message: message}
	if customErr, ok := err.(customError); ok {
		return customError{errorType: customErr.errorType, originalError: customErr.originalError, contextInfo: context}
	}

	return customError{errorType: NoType, originalError: err, contextInfo: context}
}

func GetErrorContext(err error) map[string]string {
	emptyContext := errorContext{}
	if customErr, ok := err.(customError); ok || customErr.contextInfo != emptyContext {

		return map[string]string{"field": customErr.contextInfo.Field, "message": customErr.contextInfo.Message}
	}

	return nil
}

func GetType(err error) ErrorType {
	if customErr, ok := err.(customError); ok {
		return customErr.errorType
	}

	return NoType
}

func (t ErrorType) Message() string {
	switch t {
	case BadRequest:
		return "Invalid request. Please check your input and try again."
	case NotFound:
		return "The item you requested could not be found."
	case NilUserId:
		return "User identification is missing. Please log in and try again."
	case NilRole:
		return "Role information is required to complete this action."
	case NotSaved:
		return "We couldn't save your changes. Please try again later."
	case WrongType:
		return "The data format is incorrect. Please check and resubmit."
	case EmptyData:
		return "Required information is missing. Please fill all necessary fields."
	default:
		return "An unexpected error occurred. Our team has been notified."
	}
}

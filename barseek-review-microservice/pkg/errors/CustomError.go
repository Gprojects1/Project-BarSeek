package errors

import (
	"fmt"

	"github.com/pkg/errors"
)

const (
	NoType = ErrorType(iota)
	BadRequest
	NotFound
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

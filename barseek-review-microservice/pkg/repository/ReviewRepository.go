package repository

import (
	"barseek-review-microservice/pkg/model"
	"errors"

	"gorm.io/gorm"
)

type ReviewRepository[T any] interface {
	Save(review *T) (*T, error)
	FindById(id uint) (*T, error)
	FindAllByEntityId(entityId uint) ([]T, error)
	DeleteById(id uint) error
	FindAllByUserId(id uint) ([]T, error)
}

type reviewRepository[T any] struct {
	db *gorm.DB
}

func NewReviewRepository[T any](db *gorm.DB) ReviewRepository[T] {
	return &reviewRepository[T]{db: db}
}

func (r *reviewRepository[T]) Save(review *T) (*T, error) {
	if err := r.db.Save(review).Error; err != nil {
		return nil, err
	}
	return review, nil
}

func (r *reviewRepository[T]) FindById(id uint) (*T, error) {
	var review T
	if err := r.db.First(&review, id).Error; err != nil {
		return nil, err
	}
	return &review, nil
}

func (r *reviewRepository[T]) FindAllByEntityId(entityId uint) ([]T, error) {
	var reviews []T
	var err error

	switch any(new(T)).(type) {
	case *model.BarReview:
		err = r.db.Where("bar_id = ?", entityId).Find(&reviews).Error
	case *model.DrinkReview:
		err = r.db.Where("drink_id = ?", entityId).Find(&reviews).Error
	default:
		return nil, errors.New("unsupported review type")
	}

	if err != nil {
		return nil, err
	}
	return reviews, nil
}

func (r *reviewRepository[T]) DeleteById(id uint) error {
	return r.db.Where("id = ?", id).Delete(new(T)).Error
}

func (r *reviewRepository[T]) FindAllByUserId(id uint) ([]T, error) {
	var reviews []T
	if err := r.db.Where("user_id = ?", id).Find(&reviews).Error; err != nil {
		return nil, err
	}
	return reviews, nil
}

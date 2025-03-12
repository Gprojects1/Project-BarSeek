package service

import (
	"barseek-review-microservice/pkg/model"
	"barseek-review-microservice/pkg/repository"
	"errors"
)

type BarReviewService interface {
	AddBarReview(review *model.BarReview) (*model.BarReview, error)
	GetBarReviewById(id uint) (*model.BarReview, error)
	GetBarReviewsByBarId(barId uint) ([]model.BarReview, error)
	DeleteBarReviewById(id uint) error
}

type barReviewService struct {
	barReviewRepo repository.BarReviewRepository
}

func NewBarReviewService(barRepo repository.BarReviewRepository) BarReviewService {
	return &barReviewService{
		barReviewRepo: barRepo,
	}
}

func (s *barReviewService) AddBarReview(review *model.BarReview) (*model.BarReview, error) {

	if review.Content == "" {
		return nil, errors.New("review cannot be nil")
	}
	return s.barReviewRepo.Save(review)
}

func (s *barReviewService) GetBarReviewById(id uint) (*model.BarReview, error) {
	return s.barReviewRepo.FindById(id)
}

func (s *barReviewService) GetBarReviewsByBarId(barId uint) ([]model.BarReview, error) {
	return s.barReviewRepo.FindAllByEntityId(barId)
}

func (s *barReviewService) DeleteBarReviewById(id uint) error {
	return s.barReviewRepo.DeleteById(id)
}

func (s *barReviewService) GetReviewsByUserId(id uint) ([]model.BarReview, error) {
	return s.barReviewRepo.FindAllByUserId(id)
}

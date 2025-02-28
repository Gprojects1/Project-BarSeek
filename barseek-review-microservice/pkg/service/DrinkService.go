package service

import (
	"barseek-review-microservice/pkg/model"
	"barseek-review-microservice/pkg/repository"
	"errors"
)

type DrinkReviewService interface {
	AddDrinkReview(review *model.DrinkReview) (*model.DrinkReview, error)
	GetDrinkReviewById(id uint) (*model.DrinkReview, error)
	GetDrinkReviewsByDrinkId(drinkId uint) ([]model.DrinkReview, error)
	DeleteDrinkReviewById(id uint) error
}

type drinkReviewService struct {
	drinkReviewRepo repository.DrinkReviewRepository
}

func NewDrinkReviewService(drinkRepo repository.DrinkReviewRepository) DrinkReviewService {
	return &drinkReviewService{
		drinkReviewRepo: drinkRepo,
	}
}

func (s *drinkReviewService) AddDrinkReview(review *model.DrinkReview) (*model.DrinkReview, error) {
	if review == nil {
		return nil, errors.New("review cannot be nil")
	}
	return s.drinkReviewRepo.Save(review)
}

func (s *drinkReviewService) GetDrinkReviewById(id uint) (*model.DrinkReview, error) {
	return s.drinkReviewRepo.FindById(id)
}

func (s *drinkReviewService) GetDrinkReviewsByDrinkId(drinkId uint) ([]model.DrinkReview, error) {
	return s.drinkReviewRepo.FindAllByEntityId(drinkId)
}

func (s *drinkReviewService) DeleteDrinkReviewById(id uint) error {
	return s.drinkReviewRepo.DeleteById(id)
}

func (s *drinkReviewService) GetReviewsByUserId(id uint) ([]model.DrinkReview, error) {
	return s.drinkReviewRepo.FindAllByUserId(id)
}

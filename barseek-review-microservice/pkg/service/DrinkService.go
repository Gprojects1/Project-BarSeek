package service

import (
	"barseek-review-microservice/pkg/errors"
	"barseek-review-microservice/pkg/model"
	"barseek-review-microservice/pkg/repository"
)

type DrinkReviewService interface {
	AddDrinkReview(review *model.DrinkReview) (*model.DrinkReview, error)
	GetDrinkReviewById(id uint) (*model.DrinkReview, error)
	GetDrinkReviewsByDrinkId(Id uint) ([]model.DrinkReview, error)
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
	if review.Content == "" {
		return nil, errors.EmptyData.Newf(errors.EmptyData.Message())
	}
	return s.drinkReviewRepo.Save(review)
}

func (s *drinkReviewService) GetDrinkReviewById(id uint) (*model.DrinkReview, error) {
	rev, err := s.drinkReviewRepo.FindById(id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return nil, err
	}
	return rev, nil
}

func (s *drinkReviewService) GetDrinkReviewsByDrinkId(Id uint) ([]model.DrinkReview, error) {
	rev, err := s.drinkReviewRepo.FindAllByEntityId(Id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return nil, err
	}
	return rev, nil
}

func (s *drinkReviewService) DeleteDrinkReviewById(Id uint) error {
	err := s.drinkReviewRepo.DeleteById(Id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return err
	}
	return nil
}

func (s *drinkReviewService) GetReviewsByUserId(Id uint) ([]model.DrinkReview, error) {
	rev, err := s.drinkReviewRepo.FindAllByEntityId(Id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "server sight error")
		return nil, err
	}
	return rev, nil
}

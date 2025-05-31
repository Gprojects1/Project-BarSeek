package service

import (
	"barseek-review-microservice/pkg/errors"
	"barseek-review-microservice/pkg/model"
	"barseek-review-microservice/pkg/repository"
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
		return nil, errors.EmptyData.Newf(errors.EmptyData.Message())
	}
	return s.barReviewRepo.Save(review)
}

func (s *barReviewService) GetBarReviewById(id uint) (*model.BarReview, error) {
	rev, err := s.barReviewRepo.FindById(id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return nil, err
	}
	return rev, nil
}

func (s *barReviewService) GetBarReviewsByBarId(Id uint) ([]model.BarReview, error) {
	rev, err := s.barReviewRepo.FindAllByEntityId(Id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return nil, err
	}
	return rev, nil
}

func (s *barReviewService) DeleteBarReviewById(Id uint) error {
	err := s.barReviewRepo.DeleteById(Id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return err
	}
	return nil
}

func (s *barReviewService) GetReviewsByUserId(Id uint) ([]model.BarReview, error) {
	rev, err := s.barReviewRepo.FindAllByEntityId(Id)
	if err != nil {
		err = errors.Wrapf(err, errors.NotFound.Message())
		err = errors.AddErrorContext(err, "id", "error in processing request information")
		return nil, err
	}
	return rev, nil
}

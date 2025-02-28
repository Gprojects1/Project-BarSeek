package repository

import (
	"barseek-review-microservice/pkg/model"
)

type DrinkReviewRepository interface {
	ReviewRepository[model.DrinkReview]
}

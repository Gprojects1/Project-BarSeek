package repository

import (
	"barseek-review-microservice/pkg/model"
)

type BarReviewRepository interface {
	ReviewRepository[model.BarReview]
}

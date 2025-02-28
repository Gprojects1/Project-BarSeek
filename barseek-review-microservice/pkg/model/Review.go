package model

import (
	"gorm.io/gorm"
)

type BarReview struct {
	gorm.Model
	Title   string `json:"title"`
	Content string `json:"content"`
	Rating  int    `json:"rating" gorm:"check:rating >= 1 AND rating <= 5"`
	BarID   uint   `json:"bar_id"`
	UserID  uint   `json:"user_id"`
}

type DrinkReview struct {
	gorm.Model
	Title   string `json:"title"`
	Content string `json:"content"`
	Rating  int    `json:"rating" gorm:"check:rating >= 1 AND rating <= 5"`
	DrinkID uint   `json:"bar_id"`
	UserID  uint   `json:"user_id"`
}

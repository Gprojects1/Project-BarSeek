package proevents

type DrinkReviewCreatedEvent struct {
	Title   string `json:"title"`
	DrinkID int64  `json:"drink_id"`
	UserID  int64  `json:"user_id"`
}

func NewDrinkReviewCreatedEvent(title string, drinkID int64, userID int64) *DrinkReviewCreatedEvent {
	return &DrinkReviewCreatedEvent{
		Title:   title,
		DrinkID: drinkID,
		UserID:  userID,
	}
}

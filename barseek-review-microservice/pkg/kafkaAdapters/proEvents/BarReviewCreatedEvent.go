package proevents

type BarReviewCreatedEvent struct {
	Title  string `json:"title"`
	BarID  int64  `json:"bar_id"`
	UserID int64  `json:"user_id"`
}

func NewBarReviewCreatedEvent(title string, BarID int64, userID int64) *BarReviewCreatedEvent {
	return &BarReviewCreatedEvent{
		Title:  title,
		BarID:  BarID,
		UserID: userID,
	}
}

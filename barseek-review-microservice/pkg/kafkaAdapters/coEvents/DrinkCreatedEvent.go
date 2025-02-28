package coevents

type DrinkCreatedEvent struct {
	DrinkID int64  `json:"drinkId"`
	Name    string `json:"name"`
	BarID   int64  `json:"barId"`
}

func NewDrinkCreatedEvent(drinkID int64, name string, barID int64) *DrinkCreatedEvent {
	return &DrinkCreatedEvent{
		DrinkID: drinkID,
		Name:    name,
		BarID:   barID,
	}
}

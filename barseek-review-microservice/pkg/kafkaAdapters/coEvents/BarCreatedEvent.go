package coevents

type BarCreatedEvent struct {
	BarID   int64  `json:"barId"`
	Name    string `json:"name"`
	Address string `json:"address"`
	OwnerID int64  `json:"ownerId"`
}

func NewBarCreatedEvent(barID int64, name, address string, ownerID int64) *BarCreatedEvent {
	return &BarCreatedEvent{
		BarID:   barID,
		Name:    name,
		Address: address,
		OwnerID: ownerID,
	}
}

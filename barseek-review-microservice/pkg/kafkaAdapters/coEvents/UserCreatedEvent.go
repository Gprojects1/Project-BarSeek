package coevents

type UserCreatedEvent struct {
	UserID int64  `json:"userId"`
	Email  string `json:"email"`
	Role   string `json:"role"`
}

func NewUserCreatedEvent(userId int64, email, role string) *UserCreatedEvent {
	return &UserCreatedEvent{
		UserID: userId,
		Email:  email,
		Role:   role,
	}
}

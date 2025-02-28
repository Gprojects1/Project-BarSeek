package producer

import (
	proevents "barseek-review-microservice/pkg/kafkaAdapters/proEvents"
	"context"
	"encoding/json"
	"log"

	"github.com/segmentio/kafka-go"
)

type KafkaDrinkReviewProducerService struct {
	kafkaWriter *kafka.Writer
	topicName   string
}

func NewKafkaDrinkReviewProducerService(brokers []string, topicName string) *KafkaDrinkReviewProducerService {
	writer := kafka.NewWriter(kafka.WriterConfig{
		Brokers: brokers,
		Topic:   topicName,
	})
	return &KafkaDrinkReviewProducerService{
		kafkaWriter: writer,
		topicName:   topicName,
	}
}

func (s *KafkaDrinkReviewProducerService) SendDrinkReviewCreatedEvent(event proevents.DrinkReviewCreatedEvent) {
	eventBytes, err := json.Marshal(event)
	if err != nil {
		log.Fatalf("failed to marshal event: %v", err)
	}

	err = s.kafkaWriter.WriteMessages(context.Background(),
		kafka.Message{
			Key:   []byte("review_created"),
			Value: eventBytes,
		},
	)
	if err != nil {
		log.Fatalf("failed to send message: %v", err)
	}
}

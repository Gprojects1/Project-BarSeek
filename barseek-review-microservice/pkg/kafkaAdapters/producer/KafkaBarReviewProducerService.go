package producer

import (
	proevents "barseek-review-microservice/pkg/kafkaAdapters/proEvents"
	"context"
	"encoding/json"
	"log"

	"github.com/segmentio/kafka-go"
)

type KafkaBarReviewProducerService struct {
	kafkaWriter *kafka.Writer
	topicName   string
}

func NewKafkaBarReviewProducerService(brokers []string, topicName string) *KafkaBarReviewProducerService {
	writer := kafka.NewWriter(kafka.WriterConfig{
		Brokers: brokers,
		Topic:   topicName,
	})
	return &KafkaBarReviewProducerService{
		kafkaWriter: writer,
		topicName:   topicName,
	}
}

func (s *KafkaBarReviewProducerService) SendBarReviewCreatedEvent(event proevents.BarReviewCreatedEvent) {
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

package middleware

import (
	"barseek-review-microservice/pkg/errors"
	"net/http"

	"github.com/gin-gonic/gin"
)

func CheckHeaders() gin.HandlerFunc {
	return func(c *gin.Context) {
		userID := c.Request.Header.Get("X-User -ID")
		userRole := c.Request.Header.Get("X-User -Role")

		if userID == "" {
			c.JSON(http.StatusBadRequest, gin.H{"error": errors.NilUserId.Message()})
			c.Abort()
			return
		}

		if userRole == "" {
			c.JSON(http.StatusBadRequest, gin.H{"error": errors.NilRole.Message()})
			c.Abort()
			return
		}
		c.Next()
	}
}

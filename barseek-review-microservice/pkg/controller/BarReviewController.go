package controller

import (
	"barseek-review-microservice/pkg/middleware"
	"barseek-review-microservice/pkg/model"
	"barseek-review-microservice/pkg/service"
	"net/http"

	"strconv"

	"github.com/gin-gonic/gin"
)

type BarReviewController interface {
	AddBarReview(c *gin.Context)
	GetBarReviewById(c *gin.Context)
	GetBarReviewsByBarId(c *gin.Context)
	DeleteBarReviewById(c *gin.Context)
}

type barReviewController struct {
	BarService service.BarReviewService
}

func (con *barReviewController) AddBarReview(c *gin.Context) {
	var rev model.BarReview

	if err := c.ShouldBindJSON(&rev); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	NewRev, err := con.BarService.AddBarReview(&rev)
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusCreated, gin.H{"message": "New bar created with Title: " + NewRev.Title})

}

func (con *barReviewController) GetBarReviewById(c *gin.Context) {
	Id, err := strconv.ParseUint(c.Param("ID"), 10, 64)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid bar ID"})
		return
	}

	Rev, err := con.BarService.GetBarReviewById(uint(Id))
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusOK, Rev)
}

func (con *barReviewController) GetBarReviewsById(c *gin.Context) {
	Id, err := strconv.ParseUint(c.Param("ID"), 10, 64)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid bar ID"})
		return
	}

	Rev, err := con.BarService.GetBarReviewsByBarId(uint(Id))
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusOK, Rev)
}

func (con *barReviewController) DeleteBarReviewById(c *gin.Context) {
	Id, err := strconv.ParseUint(c.Param("ID"), 10, 64)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid bar ID"})
		return
	}

	err = con.BarService.DeleteBarReviewById(uint(Id))
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusCreated, gin.H{"message": "Bar review deleted with id: "})
}

package controller

import (
	"barseek-review-microservice/pkg/middleware"
	"barseek-review-microservice/pkg/model"
	"barseek-review-microservice/pkg/service"
	"net/http"

	"strconv"

	"github.com/gin-gonic/gin"
)

type DrinkReviewController interface {
	AddDrinkReview(c *gin.Context)
	GetDrinkReviewById(c *gin.Context)
	GetDrinkReviewsByBarId(c *gin.Context)
	DeleteDrinkReviewById(c *gin.Context)
}

type drinkReviewController struct {
	DrinkService service.DrinkReviewService
}

func (con *drinkReviewController) AddBarReview(c *gin.Context) {
	var rev model.DrinkReview

	if err := c.ShouldBindJSON(&rev); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	NewRev, err := con.DrinkService.AddDrinkReview(&rev)
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusCreated, gin.H{"message": "New bar created with Title: " + NewRev.Title})

}

func (con *drinkReviewController) GetDrinkReviewById(c *gin.Context) {
	Id, err := strconv.ParseUint(c.Param("ID"), 10, 64)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid bar ID"})
		return
	}

	Rev, err := con.DrinkService.GetDrinkReviewById(uint(Id))
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusOK, Rev)
}

func (con *drinkReviewController) GetDrinkReviewsById(c *gin.Context) {
	Id, err := strconv.ParseUint(c.Param("ID"), 10, 64)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid bar ID"})
		return
	}

	Rev, err := con.DrinkService.GetDrinkReviewsByDrinkId(uint(Id))
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusOK, Rev)
}

func (con *drinkReviewController) DeleteDrinkReviewById(c *gin.Context) {
	Id, err := strconv.ParseUint(c.Param("ID"), 10, 64)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": "Invalid bar ID"})
		return
	}

	err = con.DrinkService.DeleteDrinkReviewById(uint(Id))
	if err != nil {
		middleware.HandleError(c, err)
	}

	c.JSON(http.StatusCreated, gin.H{"message": "Bar review deleted with id: "})
}

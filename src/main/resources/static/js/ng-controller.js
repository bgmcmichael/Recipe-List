angular.module('TIYAngularApp', [])
   .controller('SampleController', function($scope, $http) {
        $scope.name = "Ben";

        $scope.getRecipes = function() {
            console.log("About to go get me some data!");
            $scope.name = "JavaScript Master Guru";

            $http.get("http://localhost:8080/recipes.json")
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.recipes = response.data;
                        console.log($scope.recipes);
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
            };

        $scope.deleteRecipe = function(recipeID) {
            console.log("About to delete recipe with ID " + recipeID);

            $http.get("/deleteRecipe.json?recipeID=" + recipeID)
                .then(
                    function success(response) {
                        console.log(response.data);
                        console.log("Recipe deleted");
                        $scope.recipes = response.data;

                        $scope.recipes = {};

//                          alert("About to refresh the recipes on the scope");

                        $scope.recipes = response.data;
                    },
                    function error(response) {
                        console.log("Unable to delete recipe");
                    });
        };

        $scope.addRecipe = function() {
            console.log("About to add the following recipe " + JSON.stringify($scope.newRecipe));

            $http.post("/modifyRecipe.json", $scope.modRecipe)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.recipes = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        $scope.addRecipe = function() {
            console.log("About to add the following recipe " + JSON.stringify($scope.newRecipe));

            $http.post("/addRecipe.json", $scope.newRecipe)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        $scope.recipes = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };
        $scope.recipes = {};
        $scope.newRecipe = {};
    });
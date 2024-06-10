angular.module('cart', []).controller("CartController", function ($scope, $http) {
    const contextPath = 'http://localhost:8081/api/v1';
    $scope.getUser = function () {
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        // if ($scope.User == null) {
        //     window.location.replace('http://localhost:8081/logIn.html');
        // }
        console.log($scope.User);
    };

    $scope.showCart = function () {
        const url = contextPath + '/cart/user/' + $scope.User.id;
        $scope.TotalPrice=0;
        console.log("Method showCart(), url: " + url);
        console.log($scope.User.id)
        $http.get(url)
            .then(function (response) {
            console.log(response.data)
            $scope.Cart = response.data;
            for(let i=0;i<$scope.Cart.length;i++){
                $scope.TotalPrice+=$scope.Cart[i].product.price*$scope.Cart[i].count;
            }
        });
    };
    $scope.clearCart = function (){
        const url = contextPath + '/cart/'+$scope.User.id;
        $http.delete(url)
            .then(function (response){
                console.log(response.data);
                location.reload();
            });
    }

    $scope.Buy=function (){
            window.location.replace('http://localhost:8081/CheckOutForm.html');
    };


    $scope.getUser()
    $scope.showCart();
});

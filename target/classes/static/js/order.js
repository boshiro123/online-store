angular.module('order', []).controller("OrderController", function ($scope, $http) {
    const contextPath = 'http://localhost:8081/api/v1';
    $scope.getUser = function () {
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        // if ($scope.User == null) {
        //     window.location.replace('http://localhost:8081/logIn.html');
        // }
        console.log($scope.User);
    };
    $scope.Orders=[]
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
                    $scope.Orders[i] = {
                        product_name: $scope.Cart[i].product.title,
                        cost: $scope.Cart[i].product.price,
                        user_id: $scope.User.id,
                        seller_id: $scope.Cart[i].product.user_creater_id
                    };
                    $scope.TotalPrice+=$scope.Cart[i].product.price*$scope.Cart[i].count;
                }
            });
    };

    $scope.Buy=function (){
        const url = contextPath + '/orders';
        console.log($scope.Orders)
        $http.post(url,$scope.Orders)
            .then(function (response){
                $scope.addSales();
                $scope.clearCart();
            });

    }
    $scope.addSales=function (){
        const url = contextPath + '/product/sales';
        console.log($scope.Cart)
        $http.put(url,$scope.Cart)
            .then(function (response){
            });

    }
    $scope.clearCart = function (){
        const url = contextPath + '/cart/'+$scope.User.id;
        $http.delete(url)
            .then(function (response){
                console.log(response.data);
                location.reload();
            });
    }


    $scope.getUser()
    $scope.showCart();
});

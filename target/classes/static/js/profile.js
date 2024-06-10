angular.module('profile',[]).controller("ProfileController",function ($scope,$http) {
    const contextPath = 'http://localhost:8081/api/v1';
    $scope.getUser=function (){
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        if($scope.User==null){
            // window.location.replace('http://localhost:8081/logIn.html');
        }
        console.log($scope.User);
        if($scope.User.status.id==1)
        {
            $scope.StatusName="Покупатель";
            $scope.ProfileHref="profile.html";

        }
        else {
            $scope.StatusName="Продавец";
            $scope.ProfileHref="AdminProfile.html";
        }

    };

    $scope.BecomeSeller=function (){
        const url = contextPath+"/user/seller"
        $http.put(url,$scope.User.id)
            .then(function (response){
                $scope.User.status.id=2;
                $scope.User.status.name="seller";
                localStorage.setItem("_user", JSON.stringify($scope.User));
                // window.location.replace('http://localhost:8081/AdminProfile.html');
            });
    }

    $scope.changeUser = function () {
        const url = contextPath + '/user/' + $scope.User.id;
        console.log("Method changeUser(), url: " + url + ", User=" + $scope.User);

        $http.put(url, $scope.User)
            .then(function (resp) {
                localStorage.clear();
                localStorage.setItem("_user", JSON.stringify($scope.User));
            });
    };




    $scope.openHistory=function (){
        window.location.replace('http://localhost:8081/history.html');
    };

    $scope.saveProduct = function () {
        const url = contextPath + '/product';
        console.log("Method saveProduct(), url: " + url);

        $scope.NewProduct.category = $scope.NewCategory;
        $scope.NewProduct.user_creater_id=$scope.User.id;
        $scope.NewProduct.likes=0;
        console.log($scope.NewProduct);
        $http.post(url, $scope.NewProduct)
            .then(function (response) {
                $scope.NewProduct = null;
                $scope.NewCategory = null;
            });
    };
    $scope.getCategories = function () {
        const url = contextPath + '/category';
        console.log("Method getCategories(), url: " + url);
        $http.get(url)
            .then(function (resp) {
                $scope.Categories = resp.data;
            });
    };
    $scope.getMyProducts=function (){
        const url = contextPath + '/product/my/'+ $scope.User.id;
        $http.get(url)
            .then(function (response){
                $scope.Products=response.data;
                console.log(response.data);
            });
    };
    $scope.changePrice = function (product, newPrice) {
        const url = contextPath + '/product/' + product.id;
        console.log("Method changePrice(), url: " + url + ", newPrice=" + newPrice);

        // Создать новый пустой объект
        let newProduct = {};

        // Скопировать все свойства product в новый объект
        for (let key in product) {
            newProduct[key] = product[key]
        }

        // Изменить цену
        newProduct.price = newPrice;
        console.log(newProduct);
        $http.put(url, newProduct)
            .then(function (resp) {
                $scope.getMyProducts();
            });
    };
    $scope.deleteProduct = function (productId) {
        const url = contextPath + '/product/delete/' + productId;
        console.log("Method deleteProduct(), url: " + url);
        $http.delete(url)
            .then(function (response) {
                location.reload();
            });
    };
    $scope.deleteFromLikes = function (product) {
        const url = contextPath + '/likes/delete/' + product.id;
        console.log("Method deleteFromLikes(), url: " + url);
        console.log(product)
        $http.delete(url)
            .then(function (response) {
                $scope.getLikes()
            });
    };
    $scope.getLikes=function (){
        const url = contextPath + '/likes/'+$scope.User.id;
        $http.get(url)
            .then(function (response){
                console.log(response.data);
                $scope.Cart=response.data;
            });

    };

    $scope.getOrders=function (){
        const url = contextPath + '/orders/'+$scope.User.id;
        $http.get(url)
            .then(function (response){
                console.log(response.data);
                $scope.Orders=response.data;
            });

    };

    $scope.takeOrder= function (order){
        const url = contextPath + '/orders/takeOrder'
        console.log(order)
        $http.put(url,order)
            .then(function (response){
                $scope.getOrders()
            });
    }

    $scope.getUser();
    $scope.getCategories();
    $scope.getMyProducts();
    $scope.getLikes();
    $scope.getOrders();
});

const showOrHidePassword = () => {
    const password = document.getElementById('password');
    if (password.type === 'password') {
        password.type = 'text';
    } else {
        password.type = 'password';
    }
};
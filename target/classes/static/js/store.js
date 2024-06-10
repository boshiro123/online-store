angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.Count=null;
    $scope.IsSort=false;
    const contextPath = 'http://localhost:8081/api/v1'; // dev-профиль
    // const contextPath = 'http://onlinestore-env.eba-zt3tmkep.eu-central-1.elasticbeanstalk.com/api/v1'; // prod-профиль
    console.log("contextPath: " + contextPath);

    $scope.logout = function () {
        window.location.replace('http://localhost:8081/logIn.html');
        localStorage.clear();
    };
    $scope.getUser=function (){
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        if($scope.User==null){
           // window.location.replace('http://localhost:8081/logIn.html');
        }
        if ($scope.User.status.id==1)$scope.ProfileHref="profile.html";
        else $scope.ProfileHref="AdminProfile.html";
        //localStorage.setItem("_user", JSON.stringify($scope.User));
    };

    $scope.fillTable = function (pageIndex = 1) {
        let url;
        if($scope.IsSort)  url = contextPath + '/product/sort';
        else  url = contextPath + '/product';
        console.log("Method fillTable(), url: " + url);
        $http({
            url: url,
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                pageIndex: pageIndex
            }
        }).then(function (response) {
            $scope.ProductPage = response.data;
            console.log($scope.ProductPage);

            let minPageIndex = pageIndex - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 2;
            if (maxPageIndex > $scope.ProductPage.totalPages) {
                maxPageIndex = $scope.ProductPage.totalPages;
            }

            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
            $scope.getCategories();
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        console.log("Method generatePagesIndexes(), startPage=" + startPage + ", endPage=" + endPage);
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.getCategories = function () {
        const url = contextPath + '/category';
        console.log("Method getCategories(), url: " + url);
        $http.get(url)
                .then(function (resp) {
                    $scope.Categories = resp.data;
                });
    };


    $scope.addToCart = function (product) {
        const url = contextPath + '/cart';
        console.log("Method addToCart(), url: " + url);
        console.log(product.count);
        let Cart ={
            user_id: $scope.User.id,
            product: product,
            count: product.count
        }
        if (typeof(product.count) == "undefined")Cart.count=1;
        $http.post(url, Cart)
                .then(function (response) {

                });
    };

    $scope.sort=function (pageIndex){
        if ($scope.IsSort)$scope.IsSort=false;
        else $scope.IsSort=true;
        $scope.fillTable();
    };
    $scope.addToLikes = function (productId) {
        console.log("Метод пошёл")
        const url1 = contextPath + '/likes/' + productId;
        const url2 = contextPath + '/product/like/' + productId;
        $http.post(url1,$scope.User.id)
            .then(function (response) {
            });
        $http.put(url2)
            .then(function (response){
            });
    };


    $scope.getUser();
    $scope.fillTable();

});

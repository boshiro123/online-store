angular.module('register',[]).controller("RegisterController",function ($scope,$http){
    const contextPath = 'http://localhost:8081/api/v1'
    $scope.saveUser = function () {
        const url = contextPath + '/user';
        console.log("Method saveUser(), url: " + url);
        console.log($scope.NewUser);
        $http.post(url, $scope.NewUser)
            .then(function (response) {
                if(response)window.location.replace('http://localhost:8081/logIn.html');
                if(response.data)alert("Регистрация успешно завершена!");
                $scope.NewUser = null;
            });
    };
});
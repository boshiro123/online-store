localStorage.clear();
angular.module('logIn',[]).controller("LogInController",function ($scope,$http){
    const contextPath = 'http://localhost:8081/api/v1'
    $scope.LogIn = function (name) {
        const url = contextPath + '/user/logIn';
        console.log("Method saveUser(), url: " + url);
        $http({
            url: url,
            method: 'GET',
            params:{
                name: name,
                password: $scope.User.password
            }
        }).then(function (response){
            console.log(response.data)
            if(response.data.name!=null) {
                if ($scope.User.password == response.data.password) {
                    if (response.data.status.id === 1)
                        window.location.replace('http://localhost:8081/profile.html');
                    else if (response.data.status.id === 2) window.location.replace('http://localhost:8081/AdminProfile.html');
                    else if (response.data.status.id === 3) alert("Вы заблокированы")

                    console.log(typeof response.data)
                    localStorage.setItem("_user", JSON.stringify(response.data));
                } else {
                    alert("Неверный пароль!!!")
                }
            }
            else if(response.data.name==null){
                alert("Пользователя с таким именем нет!!!")
            }
        });
    };
});

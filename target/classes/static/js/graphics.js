angular.module('chartApp', []).controller('ChartController', function($scope,$http) {
    const contextPath = 'http://localhost:8081/api/v1'
    $scope.labels = []
    $scope.data = []


    $scope.getUser=function (){
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        if($scope.User==null){
            // window.location.replace('http://localhost:8081/logIn.html');
        }
        console.log(typeof $scope.User);
        console.log($scope.User);

    };
    $scope.getLabels=function (){
        const url = contextPath+"/product/labels/id/"+$scope.User.id;
        $http.get(url)
            .then(function (resp) {
                $scope.Labels = resp.data;
                console.log("LABELS")
                console.log($scope.Labels);
                $scope.labels = resp.data
                $scope.getData()
            });

    };
    $scope.getData=function (){
        const url = contextPath+"/product/data/id/"+$scope.User.id;
        $http.get(url)
            .then(function (resp) {
                $scope.Labels = resp.data;
                console.log("Data")
                console.log($scope.Labels);
                $scope.data = resp.data
                $scope.createBar()
            });

    };
    $scope.createBar=function () {
        // Создаем график
        var ctx = document.getElementById('firstChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: $scope.labels,
                datasets: [{
                    label: 'Продажи',
                    data: $scope.data,
                    backgroundColor: 'rgba(61,238,5,0.6)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                width: 400,
                height: 300,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    $scope.getUser()
    $scope.getLabels()
});
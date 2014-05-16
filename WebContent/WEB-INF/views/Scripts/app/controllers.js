function HomeController($scope, $http) {

}

function MenuController($scope, $http) {
	$http.get("Scripts/data/menuLinks.js").success(function(links) {
		$scope.menuLinks =  links;
	});
}


function LoginController($scope, $http) {

}

function RegisterController($scope, $http) {

}

function RoomsController($scope, $http) {

}
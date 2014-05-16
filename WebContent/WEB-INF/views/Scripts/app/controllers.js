function HomeController($scope, $http) {

}

function MenuController($scope, $http) {
	var sessionkey = localStorage.getItem("sessionkey");

	$http.get("Scripts/data/menuLinks.js").success(function(menu) {
		if (sessionkey == null) {
			$scope.menuLinks = menu.loggedOutMenu;
		} else {
			$scope.menuLinks = menu.loggedInMenu;
		}
	});

}

function LoginController($scope, $http) {

}

function RegisterController($scope, $http) {

}

function RoomsController($scope, $http) {

}
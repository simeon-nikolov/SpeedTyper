var url = "/SpeedTyper";

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

function LoginController($scope, $http, $location) {
	this.loginModel = 
	{
		"username": "",
		"password": ""
	};
	
	this.login = function() {
		$http.post(url + "/user/login", this.loginModel)
			.success(function(data) {
				localStorage.setItem("sessionkey", data.sessionKey);
				$location.path('/');
				//$window.location.href = '/signin';
		});
	}
}

function RegisterController($scope, $http) {

}

function RoomsController($scope, $http) {

}
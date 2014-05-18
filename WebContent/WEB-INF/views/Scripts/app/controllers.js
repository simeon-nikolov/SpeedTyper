var url = "/SpeedTyper";
var sessionkey = localStorage.getItem("sessionkey");

function HomeController($rootScope, $http) {

}

function MenuController($rootScope, $http) {	
	$rootScope.menuFilter = "loggedIn";

	$http.get("Scripts/data/menuLinks.js").success(function(links) {
		$rootScope.menuLinks = links;

		if (isAuthenticated(sessionkey)) {
			$rootScope.menuFilter = "loggedIn";
		} else {
			$rootScope.menuFilter = "loggedOut";
		}
	}).error(function(data) {
		alert("error");
	});
}

function LogoutController($rootScope, $http, $location) {
	$http({
		method : 'PUT',
		url : url + "/user/logout",
		headers : {
			'sessionkey' : sessionkey
		}
	}).success(function(data) {
		sessionkey = "";
		localStorage.setItem("sessionkey", sessionkey);
		$location.path('/');
		$rootScope.menuFilter = "loggedOut";
	});
}

function LoginController($rootScope, $http, $location) {
	this.loginModel = {
		"username" : "",
		"password" : ""
	};

	this.login = function() {
		$http.post(url + "/user/login", this.loginModel).success(
				function(data) {
					sessionkey = data.sessionKey;
					localStorage.setItem("sessionkey", sessionkey);
					$location.path('/');
					$rootScope.menuFilter = "loggedIn";
				});
	};
}

function RegisterController($rootScope, $http, $location) {
	this.registerModel = {
		"username": "",
		"password": "",
		"email": ""
	};
	
	this.register = function() {
		$http.post(url + "/user/register", this.registerModel).success(
				function(data) {
					localStorage.setItem("sessionkey", data.sessionKey);
					$location.path('/');
					$rootScope.menuFilter = "loggedIn";
				});
	};
}

function RoomsController($scope, $http) {
	$scope.rooms = [];
	
	$http({
		method : 'GET',
		url : url + "/rooms/",
		headers : {
			'sessionkey' : sessionkey
		}
	}).success(function(rooms) {
		$scope.rooms = rooms;
	});
}

function ViewProfileController($rootScope, $http) {
	this.userModel = {
			"username": "",
			"email": "",
			"wordsPerMinute": ""
	};
	
	var self = this;
	
	$http({
		method : 'GET',
		url : url + "/user/viewprofile",
		headers : {
			'sessionkey' : sessionkey
		}
	}).success(function(user) {
		self.userModel.username = user.username;
		self.userModel.email = user.email;
		self.userModel.wordsPerMinute = user.wordsPerMinute;
	});
}

function isAuthenticated(sessionkey) {
	if (sessionkey == null || sessionkey == "") {
		return false;
	} else if (sessionkey.length != 50) {
		return false;
	} else {
		return true;
	}
}
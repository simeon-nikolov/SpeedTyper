var url = "/SpeedTyper";
var sessionkey = localStorage.getItem("sessionkey");
var userId = localStorage.getItem("userId");

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
		userId = "";
		sessionkey = "";
		localStorage.setItem("userId", userId);
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
				function(userModel) {
					$rootScope.menuFilter = "loggedIn";
					sessionkey = userModel.sessionKey;					
					$rootScope.username = userModel.username;
					userId = userModel.id;
					localStorage.setItem("sessionkey", sessionkey);
					localStorage.setItem("username", $rootScope.username);
					localStorage.setItem("userId", userId);
					$location.path('/');
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
				function(userModel) {
					$rootScope.menuFilter = "loggedIn";
					sessionkey = userModel.sessionKey;					
					$rootScope.username = userModel.username;
					userId = userModel.id;
					localStorage.setItem("sessionkey", sessionkey);
					localStorage.setItem("username", $rootScope.username);
					localStorage.setItem("userId", userId);
					$location.path('/');
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
		showRoomsGrid($scope);
	});
}

function CreateRoomController($scope, $http, $location) {
	this.isAuthenticated = isAuthenticated(sessionkey);
	
	this.roomModel = {
		"creatorId" : userId,
		"maxParticipants" : "",
		"textId" : ""
	};
	
	this.create = function() {
		$http({
			method : 'POST',
			url : url + "/rooms/create",
			data : this.roomModel,
			headers : {
				'sessionkey' : sessionkey
			}
		}).success(function(roomDetails) {
			var id = roomDetails.id;
			$location.path("/rooms/details/" + id);
		});
	}
}

function JoinRoomController($rootScope, $http, $routeParams, $location) {
	var id = $routeParams.id;
	
	$http({
		method : "PUT",
		url : url + "/rooms/join/" + id,
		headers : {
			"sessionkey" : sessionkey
		}
	}).success(function(roomDetails) {
		$rootScope.roomDetails = roomDetails;
		$location.path("/rooms/details/" + id);
	});
}

function ViewProfileController($http) {
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

function SingleRoomController($rootScope, $scope, $http, $routeParams) {
	var id = $routeParams.id;
	
	$scope.word = "";
	$scope.currentIndex = 0;
	$scope.allWords = [];
	
	$scope.checkTyping = function(ev) {
		if (ev.which == 32) {
			alert($scope.word);
			$scope.word = $scope.word.replace(/\s+/g, '');
			if ($scope.word === $scope.allWords[$scope.currentIndex]) {
				
				if ($scope.currentIndex < $scope.allWords.length) {
					$scope.currentIndex++;
				}
				$scope.word = "";
			}
		}
	}
	
	$http({
		method : "GET",
		url : url + "/rooms/details/" + id,
		headers : {
			"sessionkey" : sessionkey
		}
	}).success(function(roomDetails) {
		$rootScope.roomDetails = roomDetails;
		$scope.allWords = roomDetails.text.split(' ');
	});
	
	var username = localStorage.getItem("username");
	
	$rootScope.usernameFilter = function(otherUsername) {
		return username !== otherUsername;
	};
}

function showRoomsGrid($scope) {
	$("#rooms-grid").kendoGrid({
    	dataSource: $scope.rooms,
    	groupable: false,
        sortable: true,
        pageable: {
            refresh: true,
            pageSizes: true,
            buttonCount: 5
        },
        columns: [{
            field: "creator",
            title: "Creator",
        }, {
            field: "status",
            title: "Status",
        }, {
            field: "participants",
            title: "Participants"
        }, {
            field: "maxParticipants",
            title: "Max Participants"
        }, {
            field: "text",
            title: "Text"
        }, {
        	title: "Action",
        	template: "<a href='\\#/rooms/join/#=id#' class='k-button' title='Join game'>Join</a>", 
        	filterable: false
        }]
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
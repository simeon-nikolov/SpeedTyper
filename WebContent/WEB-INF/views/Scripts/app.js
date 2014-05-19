angular.module("SpeedTyperSPA", ['ngRoute'])
	.config(["$routeProvider", function ($routeProvider) {
	    $routeProvider
			.when("/", 
			{ 
				templateUrl: "Scripts/partials/home.html", 
				controller: HomeController 
			})
			.when("/logout", 
			{
				templateUrl: "Scripts/partials/logout.html",
				controller: LogoutController
			})
			.when("/login", 
			{
				templateUrl: "Scripts/partials/login-form.html",
				controller: LoginController
			})
			.when("/register", 
			{
				templateUrl: "Scripts/partials/register-form.html",
				controller: RegisterController
			})
			.when("/rooms/", 
			{ 
				templateUrl: "Scripts/partials/all-rooms.html", 
				controller: RoomsController 
			})
			.when("/rooms/create", 
			{ 
				templateUrl: "Scripts/partials/create-room.html", 
				controller: CreateRoomController 
			})
			.when("/rooms/join/:id", 
			{ 
				templateUrl: "Scripts/partials/join-room.html", 
				controller: JoinRoomController 
			})
			.when("/rooms/details/:id", 
			{ 
				templateUrl: "Scripts/partials/single-room.html", 
				controller: SingleRoomController 
			})
			.when("/profile", 
			{
				templateUrl: "Scripts/partials/profile.html",
				controller: ViewProfileController
			})
			.otherwise(
			{ 
				redirectTo: "/" }
			);
	}]);
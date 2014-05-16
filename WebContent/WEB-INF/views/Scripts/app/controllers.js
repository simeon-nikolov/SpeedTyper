function HomeController($scope, $http) {
	$scope.menuLinks = [{addresss:"/", title:"Home", name:"Home"},
	                    {addresss:"/rooms", title:"Create or join a typing race", name:"Play"},
	                    {addresss:"/highscore", title:"Highscore", name:"Highscore"},
	                    {addresss:"/user/view", title:"View profile", name:"Profile"},
	                    {addresss:"/user/logout", title:"Logout", name:"Logout"}];
}

function LoginController($scope, $http) {
	
}

function RegisterController($scope, $http) {
	
}

function RoomsController($scope, $http) {
	
}
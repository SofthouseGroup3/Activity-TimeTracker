function OverviewFormController(timelogService, activityService, userService) {
    var vm = this;
    vm.$onInit = $onInit;

    vm.viewAdmin = vm.viewAdmin || onAdminDidSubmit;
    vm.onReset  = vm.onReset || onAdminDidReset;

    vm.refreshTimelog = refreshTimelog;
    vm.refreshActivities = refreshActivities;
   vm.refreshUsers = refreshUsers;
    vm.showError = showError;

    function $onInit() {
        vm.refreshActivities();
       vm.refreshTimelog();
       vm.refreshUsers();
       vm.overview=[];
    }

    function onAdminDidSubmit(username) {
        return timelogService.list(username)
            .then(function searchedtimelogs(response) {
                    vm.overview= response.data;
                });
    }

    function refreshTimelog() {
        return timelogService.list().then(function refreshedTimelog(response) {
            vm.timelogs = response.data;
        });
    }

    function refreshActivities() {
        return activityService.list().then(function refreshedActivities(response) {
            vm.activities = response.data;
        });
    }

    function refreshUsers() {
        return userService.list().then(function refreshedUsers(response) {
            vm.users = response.data;
        });
    }

    function onAdminDidReset() {
        vm.date = '';
        vm.time = '';
        vm.title  = '';
        vm.overviewForm.$setPristine();
        vm.overviewForm.$setUntouched();
    }

    function showError(response) {
        alert(response.data.errors.join("\n"));
    }
}
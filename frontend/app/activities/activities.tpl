<div class="container">
    <adminnavigation class="row"></adminnavigation>

    <div class="row">
        Welcome {{vm.users.firstname+ " "+ vm.users.lastname}}
        <activity-form class="col-xs-12"></activity-form>

        <div class="col-xs-12 text-center" ng-if="!vm.hasActivities()">
            <div class="alert alert-info" role="alert">
                <h4>Please populate activities list</h4>
                <p>There are no activities yet.</p>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="repeatSelect"> Activities </label>
        <select name="repeatSelect" id="repeatSelect" ng-model="data">
            <option ng-repeat="activity in vm.activities" value="{{activity.title}}">{{activity.title}}</option>
        </select>
    </div>

    <div class="row">
        <activity class="col-xs-6" ng-repeat="activity in vm.activities" data="activity"></activity>
    </div>
</div>
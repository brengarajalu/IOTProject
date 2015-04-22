'use strict';

/**
 * @ngdoc function
 * @name yeomanAngularApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the yeomanAngularApp
 */
// Managing the poll list

angular.module('polls', [], function () {

}).controller('PollListCtrl', function ($scope) {

    $scope.polls = [
        {
            text: "Hello"
        }, {
            text: "World"
        }
    ]

    $scope.showalert = function()
    {
        alert("hellow")

    };
    //$scope.poll = {};
    $scope.vote = function() {};
    //$scope.polls = [];
})

.controller('PollItemCtrl', function ($scope) {

        $scope.poll = {};
        $scope.vote = function() {};
})

.controller('PollNewCtrl', function ($scope) {

        $scope.poll = {
            question: '',
            choices: [{ text: '' }, { text: '' }, { text: '' }]
        };
        $scope.addChoice = function() {
            $scope.poll.choices.push({ text: '' });
        };
        $scope.createPoll = function() {};
})




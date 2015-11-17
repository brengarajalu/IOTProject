/**
 * Created by brengarajulu on 4/23/2015.
 */
//All Mongo interactions should come here
<<<<<<< HEAD
angular.module('polls').factory('angService',['$http', function ($http) {
    var data={};
    var tenantbaseUrl="/api/tenant/";
    var savebaseUrl="/api/";

=======
angular.module('polls').factory('angService', function () {
    var data={};
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190
    data.CreateProject=function(){
        alert("task created");

    }
    data.CreateResource=function(){
        alert("task created");

    }

    data.CreateTask=function(){
        alert("task created");

    }
<<<<<<< HEAD

    return {
        GetTemplateFields: function (templateId) {
            return $http.get(tenantbaseUrl+templateId)
                .then(function(data) {
                   // alert(JSON.stringify(data));
                    return data;
                });
        },
        GetAllFields: function (templateId) {
            return $http.get(tenantbaseUrl+templateId)
                .then(function(data) {
                    // alert(JSON.stringify(data));
                    return data;
                });
        },
        CreateUser: function (user) {
            return $http.post('/api/1/createUser',user)
                .success(function(data, status, headers, config) {
                    alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("error");
                    alert(status);
                });
        },

        CreateProject: function (projectFields) {
            console.log("got hit for save project");
            console.log("field to save"+JSON.stringify(projectFields));
            return $http.post('/api/1/1/create-project',projectFields)
                .success(function(data, status, headers, config) {
                    alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("error");
                    alert(status);
                });
        },

        CreateBoard: function (projectFields) {
            console.log("got hit for create board");
            console.log("field to save"+JSON.stringify(projectFields));
            return $http.post('/api/3/1/createboard',projectFields)
                .success(function(data, status, headers, config) {
                    alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("error");
                    alert(status);
                });
        },
       ViewProjects: function () {
            console.log("got hit for view project");
            return $http.get('/api/1/1/viewprojects')
                .then(function(data) {
                    // alert(JSON.stringify(data));
                    return data;
                });
        },
        ViewBoards: function () {
            console.log("got hit for view bords");
            return $http.get('/api/3/1/viewboards')
                .then(function(data) {
                   // alert(JSON.stringify(data));
                    return data;
                });
        },
        CreateTask: function (task) {
            return $http.post('/api/1/1/createtask',task)
                .success(function(data, status, headers, config) {
                    //alert("task created successfully"+JSON.stringify(data));
                }).
                error(function(data, status, headers, config)
                {
                   // alert("There was a problem saving this record. Please try again");

                });
        },
        CreateCard: function (card) {
            return $http.post('/api/3/1/createcard',card)
                .success(function(data, status, headers, config) {
                    //alert("task created successfully"+JSON.stringify(data));
                }).
                error(function(data, status, headers, config)
                {
                    // alert("There was a problem saving this record. Please try again");

                });
        },
        UpdateTask: function (group_id,taskList) {
            //alert("groupid"+group_id);
            return $http.post('/api/1/1/'+group_id +'/updatetask',taskList)
                .success(function(data, status, headers, config) {
                    alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("There was a problem saving this record. Please try again");
                });
         },
        UpdateCard: function (group_id,taskList) {
            //alert("groupid"+group_id);
            return $http.post('/api/3/1/'+group_id +'/updatecard',taskList)
                .success(function(data, status, headers, config) {
                   // alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    //alert("There was a problem saving this record. Please try again");
                });
        },
        ViewTasks: function (projectId) {
            console.log("got hit for view task");
            return $http.get('/api/1/1/'+projectId+'/viewtasks')
                .then(function(data) {
                    // alert(JSON.stringify(data));
                    return data;
                });
        },
        ViewCards: function (projectId) {
            console.log("got hit for view CARDS");
            return $http.get('/api/3/1/'+projectId+'/viewcards')
                .then(function(data) {
                    // alert(JSON.stringify(data));
                    return data;
                });
        },
        CreateResource: function (resource) {
            return $http.post('/api/1/1/createResource',resource)
                .success(function(data, status, headers, config) {
                    alert("resource created successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("There was a problem saving this record. Please try again");
                });
        },
        UpdateResource: function (user) {
            return $http.post('/api/1/updateresource',user)
                .success(function(data, status, headers, config) {
                    alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("There was a problem saving this record. Please try again");
                });
        },
        ViewResources: function () {
            return $http.get('/api/1/1/viewresources')
                .success(function(data, status, headers, config) {
                    //alert("saved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("There was a problem saving this record. Please try again");
                });
        },
        GetAllFields: function (user) {
            return $http.get('/api/1/1/viewall')
                .success(function(data, status, headers, config) {
                    alert("retrieved successfully"+data);
                }).
                error(function(data, status, headers, config)
                {
                    alert("error");
                    alert(status);
                });
        }








    };


   // return angService;
}]);
=======
    data.ViewTasks=function(){
        alert("task created");

    }
    return data;
});
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190

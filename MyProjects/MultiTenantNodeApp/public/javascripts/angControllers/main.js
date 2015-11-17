'use strict';

/**
 * @ngdoc function
 * @name yeomanAngularApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the yeomanAngularApp
 */
// Managing the poll list

<<<<<<< HEAD
angular.module('polls').controller('WaterfallCtrl', function ($scope,angService) {

    //ALL GLOBAL DECLARATIONS
    $scope.tasks={};
    $scope.waterfallFields=[];
    $scope.kanbanFields=[];
    $scope.fields=[];
    $scope.resourcefields=[];
    $scope.taskfields=[];
    $scope.progressfields=[];

   $scope.projectName="test"
   $scope.Description="test description"
   $scope.projectList=[];
   $scope.taskList=[];
   $scope.Group_Id="";

    $scope.User=
    {
        "user_id":"1",
        "user_name":"test",
        "email":"test@gmail.com",
        "password":"45454545"
    }
    $scope.Resources= [];
    $scope.Resource=  {

    };

    $scope.TaskStatus=
        [
             {
             "Task_Status_ID": "1",
             "Task_Status": "Yet to Start"
             },
             {
                 "Task_Status_ID": "2",
                 "Task_Status": "In-Progress"
             },
            {
                "Task_Status_ID": "3",
                "Task_Status": "Completed"
            }

        ];

    $scope.CreateUser= function()
    {
        angService.CreateUser(JSON.stringify($scope.User));
        $http.post('/api/1/createUser',  $scope.User).
            success(function(data, status, headers, config) {
                BootstrapDialog.alert('User has been created. Welcome');
            }).
            error(function(data, status, headers, config) {
                alert("error");
            });
    }

    $scope.Login= function()
    {

    }

    $scope.EditTask=function(fieldId)
    {

        //alert("record"+fieldId);
        var taskFields=[];
        $scope.Group_Id=fieldId;
        $.each($scope.taskList,function(key,value)
        {

            if(value.group_id==fieldId)
            {
                taskFields.push(value);
            }

        });
        //alert(JSON.stringify(taskFields));

        $.each($scope.waterfallFields,function(id,field)
        {
            $.each(taskFields,function(key,value)
            {
                if(field!=null && field!='') {
                    if (value.field_id == field.fieldId) {
                        //alert(field.fieldName);
                        if(field.fieldName=="Task_start_date" || field.fieldName=="Task_end_date")
                        {
                            field.value = new Date(value.field_value);
                        }
                        else {
                            field.value = value.field_value;
                        }
                        //alert(field.value);
                        field.group_id=fieldId;
                    }
                }

            });
        });

       // alert(JSON.stringify($scope.waterfallFields));
        $('#editTaskModal').modal('show')

    }



    //GET TEMPLATE FIELDS
    //Get fields for waterfall template
    angService.GetTemplateFields(1)
        .then(function(data) {
            $scope.waterfallFields=data.data[0].fields;
            //alert(JSON.stringify($scope.waterfallFields));
        });

   //END OF GLOBAL DECLARATIONS

    //CREATE PROJECT
    $scope.SaveProject=function()
    {
        $scope.fields=[];
        $.each($scope.waterfallFields,function(key,value)
        {
            if(value.module=='Project')
                $scope.fields.push(angular.copy(value));
        });
        //alert("value to save"+JSON.stringify($scope.fields));
        angService.CreateProject($scope.fields)
            .then(function(data) {
                BootstrapDialog.alert('Project has been created');
            });
    }

    //CREATE RESOURCE
    $scope.CreateResource=function()
    {
        $scope.resourcefields=[];
        $.each($scope.waterfallFields,function(key,value)
        {
            if(value.module=='Resource')
                $scope.fields.push(angular.copy(value));

        });
        angService.CreateResource($scope.fields)
            .then(function(data) {
                BootstrapDialog.alert('Resource has been created');
            });

    }


    //CREATE UPDATE TASK
    $scope.CreateTask=function()
    {
        $scope.fields=[];
        $.each($scope.waterfallFields,function(key,value)
        {
            if(value.module=='Task')
                $scope.fields.push(angular.copy(value));

        });
       // alert("fields to save"+JSON.stringify($scope.fields));
        angService.CreateTask($scope.fields).then(function(data) {
            BootstrapDialog.alert('Task Created Succesfully');
            $( '#createTaskModal' ).modal('hide').data( 'bs.modal', null );
        });
=======
angular.module('polls',  ['ngTouch', 'ui.grid','ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav'], function () {

}).controller('WaterfallCtrl', function ($scope,angService) {

    $scope.createTask= function()
    {
        var task=angService.CreateTask();

    }

    $scope.createResource= function()
    {
        alert("Resource created");

    }

    $scope.createProject= function()
    {
        alert("Project created");

    }

    $scope.viewTasks= function()
    {
        alert("Project created");

    }

    $scope.viewProgress= function()
    {
        alert("Project created");
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190

    }


<<<<<<< HEAD
    $scope.ShowCreateTask=function()
    {
        $('#createTaskModal').modal('show');
    }



    //CREATE UPDATE TASK
    $scope.UpdateTask=function()
    {
        //alert("groupid"+$scope.Group_Id);
        $scope.fields=[];
        $.each($scope.waterfallFields,function(key,value)
        {
            value.group_id=$scope.Group_Id;
            if(value.module=='Task')
                $scope.fields.push(angular.copy(value));


        });
        //alert("fields to save"+JSON.stringify($scope.fields));
        angService.UpdateTask($scope.Group_Id,$scope.fields).then(function(data) {
            $( '#myModal' ).modal('hide').data( 'bs.modal', null );
            //$('#myModal').modal('close');
        });

    }

    //VIEW PROJECTS
    $scope.ViewProjects=function()
    {
         angService.ViewProjects()
             .then(function(data) {
                // alert(JSON.stringify(data.data));
                 $scope.projectList=data.data.Projects;
                 //alert(JSON.stringify($scope.projectList));
                 //$scope.projects
             });

    }
    $scope.ViewProjects();


   $scope.Resources1=
        [
           {
                "Resource_name": "Balaji",
                "Resource_email": "test@gmail.com"
            },
            {
                "Resource_name": "Abhinav",
                "Resource_email": "test@gmail.com"
            }
        ];


    //VIEW RESOURCES
    $scope.tempResources=[];
    $scope.ViewResources=function()
    {
        var resources=[];
        angService.ViewResources()
            .then(function(data) {
                resources =data.data.Resources;
                $.each(resources,function(key,value)
                {
                    $scope.Resource.Resource_name=value.field_value;
                    $scope.Resource.field_id=value.field_value;
                    $scope.Resources.push($scope.Resource)
                });
                //alert(JSON.stringify($scope.Resources));
                //alert(JSON.stringify($scope.Resources1));
            });

    }
     $scope.ViewResources();

    $scope.ViewTasks=function(projectId)
    {
        //alert("project id"+projectId);
        angService.ViewTasks(projectId)
            .then(function(data) {
                //alert(JSON.stringify(data.data));
                $scope.taskList=data.data.Tasks;
                //alert(JSON.stringify($scope.taskList));
                //$scope.projects
            });

    }

    $scope.ViewProgress=function(projectId)
    {
        //alert("project id"+projectId);
        angService.ViewTasks(projectId)
            .then(function(data) {
                //alert(JSON.stringify(data.data));
                $scope.taskList=data.data.Tasks;
                //alert(JSON.stringify($scope.taskList));
                //$scope.projects
            });

    }


    $scope.data1=[
        { "label":"2011", "value":200000, "type":"bar" },
        { "label":"Alpha", "value":-100000, "type":"var" },
        { "label":"Beta", "value":200000, "type":"var" },
        { "label":"Delta", "value":-500000, "type":"var" },
        { "label":"Gamma", "value":-100000, "type":"var" },
        { "label":"2012", "value":-300000, "type":"bar" },
        { "label":"Alpha", "value":150000, "type":"var" },
        { "label":"Beta", "value":100000, "type":"var" },
        { "label":"Delta", "value":-35000, "type":"var" },
        { "label":"Gamma", "value":205000, "type":"var" },
        { "label":"2013", "value":120000, "type":"bar" }
    ];

    $scope.viewProgress= function()
    {
        var margins = {
                top: 12,
                left: 68,
                right: 24,
                bottom: 24
            },
            legendPanel = {
                width: 180
            },
            width = 800 - margins.left - margins.right - legendPanel.width,
            height = 300 - margins.top - margins.bottom,
            dataset = [{
                data: [{
                    task_status: 'Progress',
                    NumberOfTasks: 5
                }, {
                    task_status: 'Completed',
                    NumberOfTasks: 4
                }, {
                    task_status: 'Started',
                    NumberOfTasks: 3
                }],
                name: 'Project1'
            }

            ],
            series = dataset.map(function (d) {
                return d.name;
            }),
            dataset = dataset.map(function (d) {
                return d.data.map(function (o, i) {
                    // Structure it so that your numeric
                    // axis (the stacked amount) is y
                    return {
                        y: o.NumberOfTasks,
                        x: o.task_status
                    };
                });
            }),
            stack = d3.layout.stack();

        stack(dataset);

        var dataset = dataset.map(function (group) {
                return group.map(function (d) {
                    // Invert the x and y values, and y0 becomes x0
                    return {
                        x: d.y,
                        y: d.x,
                        x0: d.y0
                    };
                });
            }),
            svg = d3.select('#dvTaskProgress')
                .append('svg')
                .attr('width', width + margins.left + margins.right + legendPanel.width)
                .attr('height', height + margins.top + margins.bottom)
                .append('g')
                .attr('transform', 'translate(' + margins.left + ',' + margins.top + ')'),
            xMax = d3.max(dataset, function (group) {
                return d3.max(group, function (d) {
                    return d.x + d.x0;
                });
            }),
            xScale = d3.scale.linear()
                .domain([0, xMax])
                .range([0, width]),
            months = dataset[0].map(function (d) {
                return d.y;
            }),
            _ = console.log(months),
            yScale = d3.scale.ordinal()
                .domain(months)
                .rangeRoundBands([0, height], .1),
            xAxis = d3.svg.axis()
                .scale(xScale)
                .orient('bottom'),
            yAxis = d3.svg.axis()
                .scale(yScale)
                .orient('left'),
            colours = d3.scale.category10(),
            groups = svg.selectAll('g')
                .data(dataset)
                .enter()
                .append('g')
                .style('fill', function (d, i) {
                    return colours(i);
                }),
            rects = groups.selectAll('rect')
                .data(function (d) {
                    return d;
                })
                .enter()
                .append('rect')
                .attr('x', function (d) {
                    return xScale(d.x0);
                })
                .attr('y', function (d, i) {
                    return yScale(d.y);
                })
                .attr('height', function (d) {
                    return yScale.rangeBand();
                })
                .attr('width', function (d) {
                    return xScale(d.x);
                })
                .on('mouseover', function (d) {
                    var xPos = parseFloat(d3.select(this).attr('x')) / 2 + width / 2;
                    var yPos = parseFloat(d3.select(this).attr('y')) + yScale.rangeBand() / 2;

                    d3.select('#tooltip')
                        .style('left', xPos + 'px')
                        .style('top', yPos + 'px')
                        .select('#value')
                        .text(d.x);

                    d3.select('#tooltip').classed('hidden', false);
                })
                .on('mouseout', function () {
                    d3.select('#tooltip').classed('hidden', true);
                })

        svg.append('g')
            .attr('class', 'axis')
            .attr('transform', 'translate(0,' + height + ')')
            .call(xAxis);

        svg.append('g')
            .attr('class', 'axis')
            .call(yAxis);

        svg.append('rect')
            .attr('fill', 'yellow')
            .attr('width', 160)
            .attr('height', 30 * dataset.length)
            .attr('x', width + margins.left)
            .attr('y', 0);

        series.forEach(function (s, i) {
            svg.append('text')
                .attr('fill', 'black')
                .attr('x', width + margins.left + 8)
                .attr('y', i * 24 + 24)
                .text(s);
            svg.append('rect')
                .attr('fill', colours(i))
                .attr('width', 60)
                .attr('height', 20)
                .attr('x', width + margins.left + 90)
                .attr('y', i * 24 + 6);
        });
    }
    $scope.viewProgress();

=======

    $scope.tasks = [
        {
            text: "Task1"
        }, {
            text: "Task2"
        }
    ]
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190

    $scope.myData = [
        {
            "Task": "Create new Screen",
            "Startdate": "01/01/2015",
            "EndDate": "03/01/2015",
            "% Completed":"30%",
            "Status": "In-Progress"
        },

    ];

<<<<<<< HEAD

});

/*.controller('KanbanCtrl', function ($scope) {
=======
    $scope.showalert = function()
    {
        alert("hellow")

    };
    //$scope.poll = {};
    $scope.vote = function() {};
    //$scope.polls = [];
})

.controller('KanbanCtrl', function ($scope) {
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190

        $scope.poll = {};
        $scope.vote = function() {};
})

.controller('ScrumCtrl', function ($scope) {

<<<<<<< HEAD
        alert("hi");
=======
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190
        $scope.poll = {
            question: '',
            choices: [{ text: '' }, { text: '' }, { text: '' }]
        };
        $scope.addChoice = function() {
            $scope.poll.choices.push({ text: '' });
        };
        $scope.createPoll = function() {};
<<<<<<< HEAD
}) */
=======
})
>>>>>>> 145ca882316109fe42691c56c363f44f83d78190




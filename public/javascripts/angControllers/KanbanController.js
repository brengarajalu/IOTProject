'use strict';
/**
 * @ngdoc function
 * @name yeomanAngularApp.controller:ScrumCtrl
 * @description
 * # MainCtrl
 * Controller of the yeomanAngularApp
 */
// Managing the poll list

angular.module('polls').controller('KanbanCtrl', function ($scope,angService) {

    $scope.tasks={};
    $scope.KanbanFields=[];
    $scope.fields=[

    ];

    $scope.Resources=
        [
            {
                "Resource_name": "Balaji",
                "Resource_email": "test@gmail.com"
            },
            {
                "Resource_name": "Abhinav",
                "Resource_email": "test@gmail.com"
            },

        ];


    //GET TEMPLATE FIELDS
    //Get fields for Kanban template
    //alert("called");
    angService.GetTemplateFields(3)
        .then(function(data) {
            $scope.KanbanFields=data.data[0].fields;
           // alert(JSON.stringify($scope.KanbanFields));
        });

    //END OF GLOBAL DECLARATIONS


    $scope.GetDate=function()
    {

        //populate KanbanFields
    }

    //CREATE board
    $scope.SaveBoard=function()
    {
        $scope.fields=[];
        $.each($scope.KanbanFields,function(key,value)
        {
            if(value.module=='Board')
                $scope.fields.push(angular.copy(value));
        });
        //alert("value to save"+JSON.stringify($scope.fields));
        angService.CreateBoard($scope.fields)
            .then(function(data) {
                BootstrapDialog.alert('Board has been created');
            });
    }


    //CREATE RESOURCE
    $scope.CreateResource=function()
    {
        $scope.resourcefields=[];
        $.each($scope.KanbanFields,function(key,value)
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
    $scope.CreateCard=function()
    {
        $scope.fields=[];
        $.each($scope.KanbanFields,function(key,value)
        {
            if(value.module=='Card')
                $scope.fields.push(angular.copy(value));

        });
        // alert("fields to save"+JSON.stringify($scope.fields));
        angService.CreateCard($scope.fields).then(function(data) {
            BootstrapDialog.alert('Card Created Succesfully');
            $( '#createTaskModal' ).modal('hide').data( 'bs.modal', null );
        });

    }

    //CREATE UPDATE TASK
    $scope.UpdateCard=function()
    {
        //alert("groupid"+$scope.Group_Id);
        $scope.fields=[];
        $.each($scope.KanbanFields,function(key,value)
        {
            value.group_id=$scope.Group_Id;
            if(value.module=='Card')
                $scope.fields.push(angular.copy(value));


        });
        //alert("fields to save"+JSON.stringify($scope.fields));
        angService.UpdateCard($scope.Group_Id,$scope.fields).then(function(data) {
            BootstrapDialog.alert('Card Updated Successfully');
            $( '#editCardModal' ).modal('hide').data( 'bs.modal', null );
            //$('#myModal').modal('close');
        });

    }
    $scope.boardList=[];

    //VIEW PROJECTS
    $scope.ViewBoards=function()
    {

        angService.ViewBoards()
            .then(function(data) {
                // alert(JSON.stringify(data.data));
                $scope.boardList=data.data.Boards;
               // alert(JSON.stringify($scope.boardList));
                //$scope.projects
            });

    }
    $scope.ViewBoards();


    $scope.ShowCreateBoard=function()
    {
        $('#createBoardModal').modal('show');
    }


    $scope.ViewResources=function()
    {
        var resources=[];
        angService.ViewResources()
            .then(function(data) {
                resources =data.data.Resources;
                $.each(resources,function(key,value)
                {
                    //$scope.Resource.Resource_name=value.field_value;
                    $scope.Resource.field_id=value.field_value;
                    $scope.Resources.push($scope.Resource)
                });
                //alert(JSON.stringify($scope.Resources));
                //alert(JSON.stringify($scope.Resources1));
            });

    }
    //$scope.ViewResources()
    $scope.cardList=[];
    $scope.ViewCards=function(projectId)
    {
       //alert("project id"+projectId);
        angService.ViewCards(projectId)
            .then(function(data) {
                //alert(JSON.stringify(data.data));
                $scope.cardList=data.data.Cards;

                $.each($scope.cardList,function(key,field){

                    if(field.field_name=='card_lane')
                    {
                        //alert(field.field_value);
                    }
                    $('#viewBoardProgress').modal('show');

                });
                //alert(JSON.stringify($scope.taskList));
                //$scope.projects
            });

    }

    $scope.EditCard=function(fieldId)
    {

        // alert("record"+fieldId);
        var cardFields=[];
        $scope.Group_Id=fieldId;
        $.each($scope.cardList,function(key,value)
        {

            if(value.group_id==fieldId)
            {
                cardFields.push(value);
            }

        });


        $.each($scope.KanbanFields,function(id,field)
        {
            $.each(cardFields,function(key,value)
            {
                if(field!=null && field!='') {
                    if (value.field_id == field.fieldId) {
                        //alert(field.fieldName);
                        if(field.fieldName=="card_planned_start" || field.fieldName=="card_planned_end")
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

        //alert(JSON.stringify($scope.waterfallFields));
        $('#editCardModal').modal('show')

    }


    $scope.OpenProgressChart=function(boardId)
    {

          $scope.ViewCards(boardId);
    }

    $scope.InProgressData=[{
        data: [{
            card_status: 'Progress',
            NumberOfCard: 5
        }, {
            card_status: 'Completed',
            NumberOfCard: 4
        }, {
            card_status: 'Started',
            NumberOfCard: 3
        }],
        name: 'Board1'
    }

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
            width = 550 - margins.left - margins.right - legendPanel.width,
            height = 300 - margins.top - margins.bottom,
            dataset =  $scope.InProgressData,
            series = dataset.map(function (d) {
                return d.name;
            }),
            dataset = dataset.map(function (d) {
                return d.data.map(function (o, i) {
                    // Structure it so that your numeric
                    // axis (the stacked amount) is y
                    return {
                        y: o.NumberOfCard,
                        x: o.card_status
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
            svg = d3.select('#dvBoardProgress')
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
})






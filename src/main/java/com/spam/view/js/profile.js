$(document).ready(function () {
    getUser();
    attendingEvents();
    createdEvents();
    viewAttendingEvent();
    viewCreatedEvent();
    toTop();
});

function getUser()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/users/' + 1, //1 is default user (organizer) id
        success: function(userArray)
        {
            var userInfo = $('#userinfo');
            var profName = $('#profname');

            //stringify to be able to display
            var userId = JSON.stringify(userArray.userId);
            var firstN = JSON.stringify(userArray.firstName);
            var lastN = JSON.stringify(userArray.lastName);
            var isOrg = JSON.stringify(userArray.organizer);
            var role = JSON.stringify(userArray.role);
            var summary = JSON.stringify(userArray.summary);

            //to remove quotes
            var firstName = firstN.replace(/"/g,"");
            var lastName = lastN.replace(/"/g,"");
            var organizer = isOrg.replace(/"/g,"");
            var yourRole = role.replace(/"/g,"");
            var summ = summary.replace(/"/g,"");

            var header = '<p>';
            header += firstName + ' ' + lastName;
            header += '</p>';

            profName.append(header);

            var box = '<div class="card-body" id="profbox">';

            var info = '<p>';
            info += 'User Id: ' + userId + '<br>';
            info += 'Organizer: ' + organizer + '<br>';
            info += 'Role: ' + yourRole + '<br>';
            info += 'Summary: ' + summ + '<br>';
            info += '</p>';

            box += info;
            box += '</div>';

            userInfo.append(box);
        },
        error: function()
        {
            alert('Failed to get user information');
        }
    })
}

var selectedAttendingEvent;
var selectedCreatedEvent;

function attendingEvents()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events/attendee/' + 1, //1 is default user (organizer) id
        success: function(eventArray)
        {
            var attending = $('#attending');

            //check if user is attending any events
            if (eventArray === undefined || eventArray.length == 0)
            {
                var message = '<p>';
                message += 'Not attending any events';
                message += '</p>';
                attending.append(message);
            }
            else
            {
                $.each(eventArray, function(index, event)
                {
                    //stringify to be able to display
                    var eventId = JSON.stringify(event.eventId);
                    var org = JSON.stringify(event.organization);
                    var title = JSON.stringify(event.eventTitle);
                    var location = JSON.stringify(event.location);
                    var date = JSON.stringify(event.eventDate);
                    var time = JSON.stringify(event.eventTime);

                    var box = '<div class="card-body">';

                    var event = '<p id="' + eventId + '">';
                    event += 'Event Id: ' + eventId + '<br>';
                    event += title + '<br>';
                    event += 'Location: ' + location + '<br>';
                    event += 'Date: ' + date + '<br>';
                    event += 'Time: ' + time + '<br>';
                    event += '</p>';

                    box += event;
                    box += '</div>';

                    attending.append(box);

                    $('#attending').click(function(event) {
                        var clickedEvent = JSON.stringify(event.target.id);

                        var noQuotes = clickedEvent.replace(/"/g,"");
                        var chosenEventId = parseInt(noQuotes);

                        selectedAttendingEvent = chosenEventId; //to put into viewEvent url parameter
                    })
                })
            }
        },
        error: function()
        {
            alert('Failed to get attending events');
        }
    })
}

function createdEvents()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events/creator/' + 1, //1 is default user (organizer) id
        success: function(eventArray)
        {
            var createdEvents = $('#createdevents');

            if (eventArray === undefined || eventArray.length == 0)
            {
                var message = '<p>';
                message += 'Did not create any events';
                message += '</p>';
                attending.append(message);
            }
            else
            {
                $.each(eventArray, function(index, event)
                {
                 //stringify to be able to display
                    var eventId = JSON.stringify(event.eventId);

                    var org = JSON.stringify(event.organization);
                    var title = JSON.stringify(event.eventTitle);
                    var location = JSON.stringify(event.location);
                    var date = JSON.stringify(event.eventDate);
                    var time = JSON.stringify(event.eventTime);

                    var box = '<div class="card-body">';

                    var event = '<p id="' + eventId + '">';
                    //event += 'Event Id: ' + eventId + '<br>';
                    //event += 'Organization: ' + org + '<br>';
                    event += title + '<br>';
                    event += 'Location: ' + location + '<br>';
                    event += 'Date: ' + date + '<br>';
                    event += 'Time: ' + time + '<br>';
                    event += '</p>';

                    box += event;
                    box += '</div>';

                    createdEvents.append(box);

                    $('#createdevents').click(function(event) {
                        var clickedEvent = JSON.stringify(event.target.id);
                        var noQuotes = clickedEvent.replace(/"/g,"");
                        var chosenEventId = parseInt(noQuotes);

                        selectedCreatedEvent = chosenEventId; //to put into viewEvent url parameter
                    })
                })
            }
        },
        error: function()
        {
            alert('Failed to get user created events');
        }
    })
}

function viewAttendingEvent()
{
   $('#attending').click(function(event){
        $.ajax({
        type: "GET",
        url: 'http://localhost:8080/api/events/' + selectedAttendingEvent,
        success: function(eventArray) {
            //to be able to transfer the data to a different html page
            var indivInfo = JSON.stringify(eventArray);
            localStorage.setItem("indivInfo", indivInfo);

            window.location.href = "specific-event.html"; //go to other page
        },
        dataType: 'json',
        error: function()
        {
            //error usually happens because needs first click to get selectedEvent
        }
        });
   })
}

function viewCreatedEvent()
{
   $('#createdevents').click(function(event){
        $.ajax({
        type: "GET",
        url: 'http://localhost:8080/api/events/' + selectedCreatedEvent,
        success: function(eventArray) {
            //to be able to transfer the data to a different html page
            var indivInfo = JSON.stringify(eventArray);
            localStorage.setItem("indivInfo", indivInfo);

            window.location.href = "specific-event.html"; //go to other page
        },
        dataType: 'json',
        error: function()
        {
            //error usually happens because needs first click to get selectedEvent
        }
        });
   })
}

//goes to top of page when goUp button is clicked
function toTop()
{
    $(document).ready(function(){
        $(window).scrollTop(0);
    });
}
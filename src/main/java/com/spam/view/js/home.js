$(document).ready(function () {
    loadEvents();
    viewEvent();
    getAttendEvent();
    attendEvent();
    toTop();
});

var selectedEvent;

function loadEvents()
{
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events',
        success: function(eventArray)
        {
            var allEvents = $('#allevents');
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
                //event += 'Organization: ' + org + '<br>';
                event += title + '<br>';
                event += 'Location: ' + location + '<br>';
                event += 'Date: ' + date + '<br>';
                event += 'Time: ' + time + '<br>';
                event += '</p>';

                box += event;
                box += '</div>';

                allEvents.append(box);

                $('#allevents').click(function(event) {
                    var clickedEvent = JSON.stringify(event.target.id);
                    var noQuotes = clickedEvent.replace(/"/g,"");
                    var chosenEventId = parseInt(noQuotes);

                    selectedEvent = chosenEventId; //to put into viewEvent url parameter
                 })
            })
        },
        error: function()
        {
            alert('Failed to get events from API');
        }
    })
}

function viewEvent()
{
   $('#allevents').click(function(event){
        $.ajax({
        type: "GET",
        url: 'http://localhost:8080/api/events/' + selectedEvent,
        success: function(eventArray) {
            //to be able to transfer the data to a different html page
            var indivInfo = JSON.stringify(eventArray);
            localStorage.setItem("indivInfo", indivInfo)

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

var eventJoin;

function getAttendEvent() //gets the input to use in url in attendEvent
{
    $('#attendevent').click(function()
    {
        eventJoin = $('#theevent').val();
    });
}

function attendEvent()
{
    $('#attendevent').click(function() {
        $.ajax({
           type: 'PUT',
           url: 'http://localhost:8080/api/add/attendee/' + eventJoin + '/' + 1,
           success: function() {
                //clear form input
                $('#theevent').val('');
                alert('You joined this event');
           },
           error: function () {
               alert('Unable to attend event'); //if already attending event that can't add again
           }
        })
    });
}

//goes to top of page when goUp button is clicked
function toTop()
{
    $(document).ready(function(){
        $(window).scrollTop(0);
    });
}














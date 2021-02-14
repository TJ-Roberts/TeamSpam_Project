$(document).ready(function () {
    loadEvents();
    viewEvent();
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

                var box = '<div class="card-body">'

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
            alert('Failed to GET events from API');
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
            //error usally happens because needs first click to get selectedEvent
        }
        });
   })
}

//goes to top of page when button is clicked
function toTop()
{
    $(document).ready(function(){
    $(window).scrollTop(0);
    });
}
















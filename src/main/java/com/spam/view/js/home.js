$(document).ready(function () {
    loadItems();
});

function loadItems()
{
 $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/api/events',
        success: function(eventArray) //array of JSON items
        {
            var allevents = $('#allevents');
            $.each(eventArray, function(index, event)
            {
                //stringify to be able to display

                var org = JSON.stringify(event.organization);
                var title = JSON.stringify(event.eventTitle);
                var location = JSON.stringify(event.location);
                var date = JSON.stringify(event.eventDate);
                var time = JSON.stringify(event.eventTime);

                var box = '<div class="card-body">'
                var event = '<p>';
                event += 'Organization: ' + org + '<br>';
                event += 'Title: ' + title + '<br>';
                event += 'Location: ' + location + '<br>';
                event += 'Date: ' + date + '<br>';
                event += 'Time: ' + time + '<br>';
                event += '</p>';
                box += event;
                event += '</div>';

                allevents.append(box);
            })
        },
        error: function()
        {
            alert('Failed to GET from API');
        }
    })
}
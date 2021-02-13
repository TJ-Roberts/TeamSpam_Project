$(document).ready(function () {
    getIndividualData(); //TODO delete button
});

function getIndividualData() //get individual event data from other page
{
    var eventHeading = $('#eventheading');
    var specificEvent = $('#specificevent');

    var anEvent = localStorage.getItem("indivInfo");
    localStorage.clear(); //to clean the localStorage

    var eventId = JSON.parse(anEvent).eventId;
    var org = JSON.parse(anEvent).organization;
    var title = JSON.parse(anEvent).eventTitle;
    var location = JSON.parse(anEvent).location;
    var date = JSON.parse(anEvent).eventDate;
    var time = JSON.parse(anEvent).eventTime;
    var description = JSON.parse(anEvent).description;
    var theFood = JSON.parse(anEvent).foodType;

    var header = '<p>';
    header += org + ': ';
    header += title + ' ';
    header += '(event #' + eventId + ')';
    header += '</p>'

    eventHeading.append(header);

    var box = '<div class="card-body">'

    var info = '<p>';
    info += 'Location: ' + location + '<br>';
    info += 'Date: ' + date + '<br>';
    info += 'Time: ' + time + '<br>';
    info += 'Details: ' + description + '<br>';
    info += 'Available Food: ' + theFood + '<br>';
    info += '</p>';

    box += info;
    box += '</div>';
    specificEvent.append(box); //create box with info
}
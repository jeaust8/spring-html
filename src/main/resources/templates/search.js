$(document).ready(function() {
    $('#searchForm').submit(function(event) {
        event.preventDefault();
        var searchData = $('#searchInput').val();
        $.ajax({
            type: 'GET',
            url: '/search?keyword=' + searchData,
            success: function(result) {
                displaySearchResults(result);
            },
            error: function() {
                alert('Error searching data.');
            }
        });
    });
});

function displaySearchResults(data) {
    var tableBody = $('#userData');
    tableBody.empty();
    data.forEach(function(user) {
        var row = '<tr>' +

            '<td>' + user.fname + '</td>' +
            '<td>' + user.lname + '</td>' +
            '<td>' + user.email + '</td>' +
            '<td>' + user.phonenumber + '</td>' +
            '</tr>';
        tableBody.append(row);
    });
}
$(function() {
    $('#addGood').click(function(){
        console.log("newProject.js loaded");
        $('#main-table tbody').append($("#main-table tbody tr:last").clone());
    });
});

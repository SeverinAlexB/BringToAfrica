$ ->
  $.get "/persons", (persons) ->
    $.each persons, (index, person) ->
      $("#persons").append $("<li>").text person.name
  $.get "/projects", (projects) ->
    $.each projects, (index, project) ->
      $("#projects").append $("<li>").text project.name
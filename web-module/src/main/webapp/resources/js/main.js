var worldDataProvider = {
    map: "worldLow",
    getAreasFromMap: true
};

var map = AmCharts.makeChart("mapdiv", {
    type: "map",


    areasSettings: {
        autoZoom: true,
        rollOverOutlineColor: "#000000",
        selectedColor: "#BBBB00",
        color: "#BBBB00"
    },

    dataProvider: worldDataProvider

});

function handleMapObjectClick( event ) {
    console.log(event.mapObject.title);
    var elem = document.getElementsByClassName("countryName");
    elem[0].value = event.mapObject.title;
}

map.addListener( "clickMapObject", handleMapObjectClick );

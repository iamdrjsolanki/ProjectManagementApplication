var niceChartData = decodeHtml(chartData);
var chartJsonArr = JSON.parse(niceChartData);

var arrLen = chartJsonArr.length;
var numericData = [];
var labelData = [];

for(var i=0; i<arrLen; i++) {
	numericData[i] = chartJsonArr[i].value;
	labelData[i] = chartJsonArr[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },
    options: {
		title: {
			display: true,
			text: 'Project Status'
		}
	}
});

function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}
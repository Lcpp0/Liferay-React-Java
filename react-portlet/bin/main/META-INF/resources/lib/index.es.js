import React from 'react';
import ReactDOM from 'react-dom';
import PlanetComponent from './PlanetComponent'


class Game extends React.Component {
	state = {
		someData: null
	  };

	componentDidMount() {
		console.log("ComponentDidMount game ");
		var request = new XMLHttpRequest();
		request.open('GET', 'resultsPlanetList', true);
		request.onload = () => {
			if (request.status >= 200 && request.status < 400) {
			  // Success!
			  console.log("request ", request)
			  this.setState({someData: request.responseText})
			} else {
			  // We reached our target server, but it returned an error
			  // Possibly handle the error by changing your state.
			}
		  };
		  
		  request.onerror = (error) => {
			// There was a connection error of some sort.
			// Possibly handle the error by changing your state.
			console.log("Error ", error)
		  };
		  
		  request.send();
	  }
	render() {
		console.log(this.state)
		return (
				<PlanetComponent />
		);
	}
}



export default function(elementId) {
	ReactDOM.render(<Game />, document.getElementById(elementId));
}
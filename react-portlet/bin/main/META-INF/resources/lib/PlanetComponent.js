import React from 'react';
import PlanetCardComponent from './PlanetCardComponent';

export default class PlanetComponent extends React.Component {
	
	state = {
	    planetList :[],
	  };
	
	
	componentDidMount() {
		var x = document.getElementById("planetList").value;
		console.log("ComponentDidMount planet component ",x);
		console.log("X Length ",x.length);
		console.log("X typeof ",typeof(x));
		
		var result = x.slice(1,-1);
		console.log("x slicet ",result);
		result = JSON.parse(result);
		console.log("result Stringify ",result);
		console.log("result Length ",result.length);
		console.log("result typeof ",typeof(result));
		
		this.setState({ 'planetList': result}, () => {
			  console.log(this.state);
			});
		
//		var list = [];
//		result.forEach(function(element){
//          console.log(element.climate);
//          list.push(element);
//          <div class="card" style="width: 18rem;">
//	          <img class="card-img-top" src="..." alt="Card image cap" />
//	          <div class="card-body">
//	            <h5 class="card-title">{element.name}</h5>
//	            <p class="card-text">{element.climate} </p>
//	          </div>
//	        </div>
//		});

	  }
	render() {
		const {planetList} = this.state;
		const planetListItems =  planetList.map((planet,i)=>
			<PlanetCardComponent key={i}
				name={planet.name}
				climate={planet.climate}
			>
			</PlanetCardComponent>
		);
		return (
			<div className="row">
				{planetListItems.length >=1
					? <div className="col-sm-12 col-md-4 col-lg-3">{planetListItems}</div>
					: <div className="text-center">
						<h1>Hola pianola</h1>
					</div>
				}
			</div>
		);
	}
}
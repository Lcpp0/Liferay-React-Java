import React from 'react';

export default class PlanetCardComponent extends React.Component {
	
	state = {
	    listaPlanetas :[],
	  };
	
	render() {
        const {name, climate} = this.props;
		return (
			<div className="card">
	          <img className="card-img-top" src="/o/react-portlet/img/Sw_banner.jpg" alt="Card image cap" />
	          <div className="card-body">
	            <h3 className="card-title">{name}</h3>
	            <p className="card-text">{climate} </p>
	          </div>
	        </div>
		);
	}
}
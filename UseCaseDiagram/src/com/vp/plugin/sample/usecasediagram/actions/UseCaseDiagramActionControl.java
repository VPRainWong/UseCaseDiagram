package com.vp.plugin.sample.usecasediagram.actions;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IUseCaseDiagramUIModel;
import com.vp.plugin.diagram.connector.IAssociationUIModel;
import com.vp.plugin.diagram.connector.IExtendUIModel;
import com.vp.plugin.diagram.connector.IIncludeUIModel;
import com.vp.plugin.diagram.shape.IActorUIModel;
import com.vp.plugin.diagram.shape.ISystemUIModel;
import com.vp.plugin.diagram.shape.IUseCaseUIModel;
import com.vp.plugin.glossary.ModelElementInText;
import com.vp.plugin.model.IActor;
import com.vp.plugin.model.IAssociation;
import com.vp.plugin.model.IExtend;
import com.vp.plugin.model.IExtensionPoint;
import com.vp.plugin.model.IInclude;
import com.vp.plugin.model.ISystem;
import com.vp.plugin.model.IUseCase;
import com.vp.plugin.model.factory.IModelElementFactory;

public class UseCaseDiagramActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IUseCaseDiagramUIModel diagram = (IUseCaseDiagramUIModel) diagramManager.createDiagram(DiagramManager.DIAGRAM_TYPE_USE_CASE_DIAGRAM);
		
		// create actor model
		IActor actorModel = IModelElementFactory.instance().createActor();
		actorModel.setName("User");
		// create actor shape
		IActorUIModel actorUI = (IActorUIModel) diagramManager.createDiagramElement(diagram, actorModel);
		actorUI.setBounds(126, 179, 30, 60);
		actorUI.getCaptionUIModel().setBounds(115, 239, 50, 15); // specify position for the actor caption
		
		// create system boundary model
		ISystem systemModel = IModelElementFactory.instance().createSystem();
		systemModel.setName("Demo system");
		// create system boundary shape
		ISystemUIModel systemUI = (ISystemUIModel) diagramManager.createDiagramElement(diagram, systemModel);
		systemUI.setBounds(277, 132, 216, 280);	
		
		// create use cases model
		IUseCase baseUseCaseModel = IModelElementFactory.instance().createUseCase();
		baseUseCaseModel.setName("Base Use Case");
		systemModel.addChild(baseUseCaseModel);
		// create use case shape
		IUseCaseUIModel baseUseCaseUI = (IUseCaseUIModel) diagramManager.createDiagramElement(diagram, baseUseCaseModel);
		systemUI.addChild(baseUseCaseUI);
		baseUseCaseUI.setBounds(302, 189, 181, 59);

		IUseCase extendUseCaseModel = IModelElementFactory.instance().createUseCase();
		extendUseCaseModel.setName("Extend Use Case");
		systemModel.addChild(extendUseCaseModel);
		IUseCaseUIModel extendUseCaseUI = (IUseCaseUIModel) diagramManager.createDiagramElement(diagram, extendUseCaseModel);
		systemUI.addChild(extendUseCaseUI);
		extendUseCaseUI.setBounds(302, 307, 80, 40);
		
		IUseCase includeUseCaseModel = IModelElementFactory.instance().createUseCase();
		includeUseCaseModel.setName("Include Use Case");
		systemModel.addChild(includeUseCaseModel);
		IUseCaseUIModel includeUseCaseUI = (IUseCaseUIModel) diagramManager.createDiagramElement(diagram, includeUseCaseModel);
		systemUI.addChild(includeUseCaseUI);
		includeUseCaseUI.setBounds(403, 307, 80, 40);	
		
				
		// create association model between actor and base use case
		IAssociation associationModel = IModelElementFactory.instance().createAssociation();
		associationModel.setFrom(actorModel);
		associationModel.setTo(baseUseCaseModel);
		// create association connector
		IAssociationUIModel associationUI = (IAssociationUIModel) diagramManager.createConnector(diagram, associationModel, actorUI, baseUseCaseUI, null);
		
		// create include relationship between base use case and include use case
		IInclude includeModel = IModelElementFactory.instance().createInclude();
		includeModel.setFrom(baseUseCaseModel);
		includeModel.setTo(includeUseCaseModel);
		// create include connector
		IIncludeUIModel includeUI = (IIncludeUIModel) diagramManager.createConnector(diagram, includeModel, baseUseCaseUI, includeUseCaseUI, null);
		includeUI.getCaptionUIModel().setBounds(385, 270, 82, 14); // specify position for the include caption
		
		// create extend relationship between base use case and extend use case
		IExtend extendModel = IModelElementFactory.instance().createExtend();
		extendModel.setFrom(baseUseCaseModel);
		extendModel.setTo(extendUseCaseModel);
		// create extend connector
		IExtendUIModel extendUI = (IExtendUIModel) diagramManager.createConnector(diagram, extendModel, baseUseCaseUI, extendUseCaseUI, null);
		extendUI.getCaptionUIModel().setBounds(295, 270, 80, 15); // specify position for the extend caption
		
		// create extension point in base use case
		IExtensionPoint extensionPoint = IModelElementFactory.instance().createExtensionPoint();
		extensionPoint.setName("Extend Use Case");
		extendModel.setExtensionPoint(extensionPoint);
					
		// show up the diagram
		diagramManager.openDiagram(diagram);
		
}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}

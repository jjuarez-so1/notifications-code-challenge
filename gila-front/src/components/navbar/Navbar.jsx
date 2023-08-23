import "./navbar.scss"
import LanguageOutlinedIcon from '@mui/icons-material/LanguageOutlined';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircleOutlined';

const Navbar = () => {
  return (
    <div className="navbarContainer">
      <div className="wrapper">
 
        <div className="items">
          <div className="item">
            <LanguageOutlinedIcon className="icon"/>
          </div>
          <div className="item">
            <AccountCircleOutlinedIcon className="icon"/>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Navbar
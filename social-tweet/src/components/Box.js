import React  from 'react';
import LockRoundedIcon from '@mui/icons-material/LockRounded';
import LockOpenRoundedIcon from '@mui/icons-material/LockOpenRounded';

const Box = ({content, setContentFunc, sendFunc, submit, placeHolder, setStatusClick,status}) => {
    
    
    return (
        <div className="flex flex-col flex-1 mt-2 px-2">
                  <textarea
                      className="w-full text-lg placeholder-gray-dark outline-none overflow-hidden resize-none bg-transparent"
                      placeholder={placeHolder}
                      onChange={(e) => setContentFunc(e.target.value)}
                      value={content}/>
            <div className="flex items-center justify-between">
                <div className="flex -ml-1">
                    <div className="flex items-center justify-center w-11 h-11 rounded-full hover:bg-gray-lightest"
                        onClick={setStatusClick}>
                        {status===1 ?
                            <LockOpenRoundedIcon/> :
                            <LockRoundedIcon/>
                        }
                    </div>
                    
                </div>
                <button
                    className="bg-primary-base text-white rounded-full px-4 py-2 font-medium"
                    onClick={sendFunc}>
                    {submit}
                </button>
            </div>
        </div>
    );
}

export default Box;